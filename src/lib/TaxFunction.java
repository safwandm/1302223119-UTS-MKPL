package lib;

public class TaxFunction {

    public static int calculateTax(Employee employee) {
        int monthsWorked = employee.getEmploymentRecord().getMonthWorkingInYear();
        int numberOfChildren = Math.min(employee.getEmployeesChilds().size(), 3);
        int monthlySalary = employee.getEmploymentRecord().getMonthlySalary();
        int otherMonthlyIncome = employee.getEmploymentRecord().getOtherMonthlyIncome();
        int deductible = employee.getEmploymentRecord().getAnnualDeductible();

        validateMonthsWorked(monthsWorked);

        int nonTaxableIncome = calculateNonTaxableIncome(employee.getSpouse() != null, numberOfChildren);
        int taxableIncome = calculateTaxableIncome(monthlySalary, otherMonthlyIncome, monthsWorked, deductible, nonTaxableIncome);
        
        return calculateFinalTax(taxableIncome);
    }

    private static void validateMonthsWorked(int monthsWorked) {
        if (monthsWorked > 12) {
            System.err.println("More than 12 month working per year");
        }
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxable = 54000000;
        if (isMarried) {
            nonTaxable += 4500000 + (numberOfChildren * 4500000);
        }
        return nonTaxable;
    }

    private static int calculateTaxableIncome(int salary, int otherIncome, int monthsWorked, int deductible, int nonTaxable) {
        return (salary + otherIncome) * monthsWorked - deductible - nonTaxable;
    }

    private static int calculateFinalTax(int taxableIncome) {
        int tax = (int) Math.round(0.05 * Math.max(taxableIncome, 0));
        return Math.max(tax, 0);
    }
}
