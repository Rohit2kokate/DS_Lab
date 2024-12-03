import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class ReverseClient {
  public static void main(String[] args) {
    try {
      // Initialize the ORB
      ORB orb = ORB.init(args, null);

      // Get the root naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // Resolve the object reference in the Naming Service
      String name = "Reverse";
      Reverse reverseImpl = ReverseHelper.narrow(ncRef.resolve_str(name));

      // Call the reverse_string method
      String input = "CORBA Example";
      String result = reverseImpl.reverse_string(input);
      System.out.println("Input: " + input);
      System.out.println("Reversed: " + result);
    } catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
  }
}
