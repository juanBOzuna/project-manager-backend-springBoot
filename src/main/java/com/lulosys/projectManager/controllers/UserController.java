package com.lulosys.projectManager.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import com.lulosys.projectManager.ModelResponses.CreateUserModel;
import com.lulosys.projectManager.ModelResponses.LoginResponse;
import com.lulosys.projectManager.entitys.ProjectEntity;
import com.lulosys.projectManager.entitys.TaskEntity;
import com.lulosys.projectManager.entitys.UserEntity;
import com.lulosys.projectManager.services.ProjectsService;
import com.lulosys.projectManager.services.TaskService;
import com.lulosys.projectManager.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UsersService userService;
    @Autowired
    ProjectsController projectsController;
    @Autowired
    ProjectsService projectsService;

    @GetMapping()
    public ArrayList<UserEntity> index() {
        ArrayList<UserEntity> users = new ArrayList<UserEntity>();

        for (UserEntity userEntity : userService.indexService()) {
            if (userEntity.getRole() != "admin") {
                users.add(userEntity);
            } else {
                userEntity.setPassword("");
            }
        }

        return users;
    }

    @GetMapping(path = "/{id}")
    public Optional<UserEntity> get(@PathVariable("id") Long id) {
        return this.userService.obtenerPorId(id);
    }

    @GetMapping(path = "/myTask/userId={userId}")
    public TaskEntity getTaskOfEmployeeService(@PathVariable("userId") Long userId) {
        return userService.getTaskOfEmployeeService(userId);
    }

    @GetMapping(path = "/login/email={email}/pass={pass}")
    public LoginResponse login(@PathVariable("email") String email, @PathVariable("pass") String pass) {
        LoginResponse response = new LoginResponse();
        UserEntity user = new UserEntity();
        try {
            user = this.userService.getByEmailService(email);
            if (user.getPassword().equals(pass)) {
                response.setUserEntity(user);
                response.setErrors("null");

                try {
                    ProjectEntity projectEntity;
                    projectEntity = projectsController.get(user.getProjectId()).get();
                    response.setProject(projectEntity);

                } catch (Exception e) {
                    // TODO: handle exception
                }
            } else {
                response.setErrors("Contraseña incorrecta");
            }

        } catch (Exception e) {
            response.setErrors("Correo incorrecto");
            // TODO: handle exception
        }

        return response;
    }

    @GetMapping(path = "/rol={rol}")
    public ArrayList<UserEntity> getPromotors(@PathVariable("rol") String rol) {
        ArrayList<UserEntity> promotors = new ArrayList<UserEntity>();
        for (UserEntity userEntity : this.userService.getByRoleService(rol)) {
            if (userEntity.getProjectId() == null) {
                promotors.add(userEntity);
            }
        }
        return promotors;
    }

    @GetMapping(path = "/rolEdit={rolEdit}/projectId={projectId}")
    public ArrayList<UserEntity> getPromotorsForEditProject(@PathVariable("rolEdit") String rolEdit,
            @PathVariable("projectId") String projectId) {
        // UserEntity userAssigned = null;
        ArrayList<UserEntity> promotors = new ArrayList<UserEntity>();
        for (UserEntity userEntity : this.userService.getByRoleService(rolEdit)) {
            if (userEntity.getProjectId() == null) {
                promotors.add(userEntity);
            }
            try {
                if (userEntity.getProjectId() != null && userEntity.getProjectId() == Long.parseLong(projectId)) {
                    // userAssigned = new UserEntity();
                    // userAssigned = userEntity;
                    promotors.add(0, userEntity);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        return promotors;
    }

    @GetMapping(path = "/ofProyects/projectId={projectId}")
    public ArrayList<UserEntity> getEmployeesOfProyect(@PathVariable("projectId") Long projectId) {
        ArrayList<UserEntity> employees = new ArrayList<UserEntity>();
        for (UserEntity userEntity : this.userService.getByProjectIdService(projectId)) {
            if (userEntity.getRole().equals("empleado")) {
                employees.add(userEntity);
            }
        }
        return employees;
    }

    @GetMapping(path = "/editTask/projectId={projectId}/taskId={taskId}")
    public ArrayList<UserEntity> getUSersForEditTasks(@PathVariable("projectId") Long projectId,
            @PathVariable("taskId") Long taskId) {
        UserEntity userAssigned = null;
        ArrayList<UserEntity> employees = new ArrayList<UserEntity>();
        for (UserEntity userEntity : this.userService.getByProjectIdService(projectId)) {
            if (userEntity.getTaskId() != null && userEntity.getTaskId() == taskId) {
                userAssigned = userEntity;
            }

            if (userEntity.getTaskId() == null && userEntity.getRole().equals("empleado")
                    && userEntity.getTaskId() != taskId) {
                employees.add(userEntity);
            }
        }
        employees.add(0, userAssigned);
        return employees;
    }

    @GetMapping(path = "/without_project")
    public ArrayList<UserEntity> getWithoutProject() {
        ArrayList<UserEntity> employees = new ArrayList<UserEntity>();
        for (UserEntity userEntity : this.userService.indexService()) {
            if (userEntity.getProjectId() == null && userEntity.getRole().equals("empleado")) {
                employees.add(userEntity);
            }
        }
        return employees;
    }

    @GetMapping(path = "/without_task/projectId={projectId}")
    public ArrayList<UserEntity> getForProjectIdAndWithoutProject(@PathVariable("projectId") Long projectId) {
        ArrayList<UserEntity> employees = new ArrayList<UserEntity>();
        for (UserEntity userEntity : this.userService.getByProjectIdService(projectId)) {
            if (userEntity.getTaskId() == null && userEntity.getRole().equals("empleado")) {
                employees.add(userEntity);
            }
        }
        return employees;
    }

    @GetMapping(path = "/finalizeProject")
    public ArrayList<UserEntity> getUsersByProjectId(Long projectId) {
        return userService.getByProjectIdService(projectId);
    }

    // @GetMapping(path = "/project={projectId}")
    @GetMapping(path = "/projectId={projectId}")
    public UserEntity getPromotorByProjectId(@PathVariable("projectId") Long projectId) {
        return this.userService.getPromotorByProjectIdService(projectId);
    }

    @PostMapping()
    public UserEntity post(@RequestBody CreateUserModel usuario) {
        try {
            Timestamp date = Timestamp.valueOf(usuario.getDate());
            usuario.getUser().setHiring_date(date);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return this.userService.postService(usuario.getUser());
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable("id") Long id) {

        boolean ok = this.userService.deleteService(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No pudo eliminar el usuario con id" + id;
        }
    }

    UserEntity getUserByTaskId(long taskId) {
        try {
            return userService.getByTaskIdService(taskId);
        } catch (Exception e) {
            UserEntity usernn = new UserEntity();
            usernn.setName("Sin nombre");
            usernn.setId(0L);
            // TODO: handle exception
            return usernn;
        }
    }

}