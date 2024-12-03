import ReverseModule.ReversePOA;

public class ReverseImpl extends ReversePOA {
  // Constructor
  public ReverseImpl() {
    super();
    System.out.println("Reverse Object Created");
  }

  // Method to reverse a string
  @Override
  public String reverse_string(String name) {
    if (name == null) {
      return "Server Send: Invalid input (null)";
    }
    // Use StringBuilder for better performance (modern replacement for StringBuffer)
    StringBuilder str = new StringBuilder(name);
    str.reverse();
    return "Server Send: " + str.toString();
  }
}
