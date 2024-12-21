/**
 * 
 */
/**
 * 
 */
module inventory_management_system {
    requires java.rmi;
	requires java.sql;  // Requires the RMI module
    exports server;     // Export the server package to make it accessible to other modules (e.g., java.rmi)
}
