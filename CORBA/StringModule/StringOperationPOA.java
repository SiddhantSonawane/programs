package StringModule;


/**
* StringModule/StringOperationPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from StringOperation.idl
* Tuesday, April 29, 2025 2:33:51 AM UTC
*/

public abstract class StringOperationPOA extends org.omg.PortableServer.Servant
 implements StringModule.StringOperationOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("reverseString", new java.lang.Integer (0));
    _methods.put ("uppercaseString", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // StringModule/StringOperation/reverseString
       {
         String input = in.read_string ();
         String $result = null;
         $result = this.reverseString (input);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // StringModule/StringOperation/uppercaseString
       {
         String input = in.read_string ();
         String $result = null;
         $result = this.uppercaseString (input);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:StringModule/StringOperation:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public StringOperation _this() 
  {
    return StringOperationHelper.narrow(
    super._this_object());
  }

  public StringOperation _this(org.omg.CORBA.ORB orb) 
  {
    return StringOperationHelper.narrow(
    super._this_object(orb));
  }


} // class StringOperationPOA
