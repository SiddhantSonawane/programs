import stringmodule.StringOperationPOA;

public class StringOperationImpl extends StringOperationPOA {
    public String revStr(String ip) {
        return new StringBuilder(ip).reverse().toString();
    }
}