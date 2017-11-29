package infrastructure;

import javax.servlet.http.HttpServlet;

public class LombokExRunner {
    public static void main(String[] args) {

//        CustomConstructor
        ExampleClass ec = new ExampleClass("\"my text\"", false);
//        @AllArgsConstructor
        ExampleClass ec1 = new ExampleClass(3, "32", false);
//        CustomConstructor
        ExampleClass ec2 = new ExampleClass("43", true);
//        @RequiredArgsConstructor
        ExampleClass ec3 = new ExampleClass("req");

        ec.setText("mam");
        System.out.println(ec + "\n" + ec1 + "\n" + ec2 + "\n" + ec3);
        System.out.println(ec2.isReadable());

        System.out.println("EQUALS and Hash Code");
        System.out.println(ec.hashCode() + "\n" + ec1.hashCode() + "\n" +
                ec2.hashCode() + "\n" +
                ec3.hashCode());

        StringBuffer buffer = new StringBuffer();
        buffer.append("frfr");
        buffer.substring(1,2);

        String f ="01234";
        System.out.println(f.substring(0,1));
        HttpServlet ff;
    }
}
