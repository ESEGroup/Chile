package api.config;


import api.controllers.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Basic JAX-RS application.
 *
 * @author Ivo Woltring
 */
@ApplicationPath("/api")
public class WebApiConfig extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        final Set<Class<?>> resources = new java.util.HashSet<>();

        addRestResourceClasses(resources);

        return resources;
    }

    /**
     * Add your own resources here.
     */
    private void addRestResourceClasses(final Set<Class<?>> resources)
    {
        resources.add(LoginController.class);
        resources.add(UserController.class);
        resources.add(RoleController.class);
        resources.add(DepartmentController.class);
        resources.add(EquipmentController.class);
        resources.add(MaintenanceController.class);

    }

}
