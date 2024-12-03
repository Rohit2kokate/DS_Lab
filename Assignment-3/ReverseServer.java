import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class ReverseServer {
  public static void main(String[] args) {
    try {
      // Initialize the ORB
      ORB orb = ORB.init(args, null);

      // Get reference to the RootPOA and activate the POA manager
      POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootPOA.the_POAManager().activate();

      // Create the implementation and register it with the ORB
      ReverseImpl reverseImpl = new ReverseImpl();
      org.omg.CORBA.Object ref = rootPOA.servant_to_reference(reverseImpl);
      Reverse href = ReverseHelper.narrow(ref);

      // Get the root naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // Bind the object in the naming context
      String name = "Reverse";
      NameComponent[] path = ncRef.to_name(name);
      ncRef.rebind(path, href);

      System.out.println("Reverse Server ready and waiting...");

      // Wait for client requests
      orb.run();
    } catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
    System.out.println("Server Exiting...");
  }
}
