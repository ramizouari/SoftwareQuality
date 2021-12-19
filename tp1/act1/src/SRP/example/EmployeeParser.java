package SRP.example;

public class EmployeeParser {
    public static String toHtml(Employee employee) {
        String str = "";
        TagBuilder div = new TagBuilder().setTag("div");
        TagBuilder span = new TagBuilder().setTag("span");
        TagBuilder h1 = new TagBuilder().setTag("h1");

        str +=  div.getOpeningTag() +
                h1.setContent("Employee Info") +
                div.setAttribute("id","emp"+employee.getEmpId()) +
                span.setContent(employee.getName()) +
                div.setAttribute("class", "left").setContent(
                    "" +
                    span.setContent("Leaves Left :") +
                    span.setContent("Annual Left :") +
                    span.setContent("Manager:") +
                    span.setContent("Reimbursable leaves")
                );

        str+= div.setAttribute("class", "right")
                .setContent(span.setContent(employee.getLeavesLeft()));

        str += span.setContent(employee.getYearlySalary());
        str += span.setContent(employee.getManager());
        str += span.setContent(employee.getTotalLeavesLeftPreviously());
        str += div.getClosingTag() + div.getClosingTag();

        return str;
    }
}
