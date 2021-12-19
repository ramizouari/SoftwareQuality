package SRP.example;

public class Employee {
    private static final int LEAVES_ALLOWED = 27;
    private int _empId;
    private String _name;
    private double _monthlySalary;
    private String _manager;
    private int _leavesTaken;
    private int _yearsInOrg;
    private int[] _leavesLeftPreviously;


    public Employee(int empId, double monthlySalary, String manager, String name, int leavesTaken,
                    int[] leavesLeftPreviously)
    {
        _empId = empId;
        _name = name;
        _monthlySalary = monthlySalary;
        _manager = manager;
        _leavesTaken = leavesTaken;
        _leavesLeftPreviously = leavesLeftPreviously;
        _yearsInOrg = leavesLeftPreviously.length;
    }

    public String getManager() {
        return (_manager != null)?_manager:"None" ;
    }

    public int getEmpId() {
        return this._empId;
    }

    public String getName() {
        return _name;
    }
    public int getLeavesLeft() {
        return LEAVES_ALLOWED - _leavesTaken;
    }

    public int getTotalLeavesLeftPreviously() {
        int years = 3;
        if (_yearsInOrg < 3) {
            years = _yearsInOrg;
        }
        int totalLeavesLeftPreviously = 0;
        for (int i = 0; i < years; i++) {
            totalLeavesLeftPreviously += _leavesLeftPreviously[_yearsInOrg - i - 1];
        }
        return totalLeavesLeftPreviously;
    }

    public long getYearlySalary() {
        return Math.round(_monthlySalary * 12);
    }


}
