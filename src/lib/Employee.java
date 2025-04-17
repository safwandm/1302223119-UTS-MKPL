package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

enum Gender {
	MALE, FEMALE
}

public class Employee {

	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;

	private Gender gender;

	private boolean isForeigner;

	private Spouse spouse;

	private List<Child> childs;

	private EmploymentRecord employmentRecord;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
			int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.isForeigner = isForeigner;
		this.gender = gender;

		this.employmentRecord = new EmploymentRecord(employeeId, yearJoined, monthJoined, dayJoined);
	}

	public EmploymentRecord getEmploymentRecord() {
		return employmentRecord;
	}

	public List<Child> getEmployeesChilds() {
		return getEmployeesChilds();
	}

	public Spouse getSpouse() {
		return spouse;
	}

	public void setSpouse(Spouse spouse) {
		this.spouse = spouse;
	}

	public void addChild(Child child) {
		childs.add(child);
	}

	public int getAnnualIncomeTax() {

		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah
		// bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		return TaxFunction.calculateTax(this);
	}
}

class EmploymentRecord {
	private String employeeId;
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	public EmploymentRecord(String employeeId, int yearJoined, int monthJoined, int dayJoined) {
		this.employeeId = employeeId;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public int getYearJoined() {
		return yearJoined;
	}

	public int getMonthJoined() {
		return monthJoined;
	}

	public int getDayJoined() {
		return dayJoined;
	}

	public int getMonthWorkingInYear() {
		return monthWorkingInYear;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public int getOtherMonthlyIncome() {
		return otherMonthlyIncome;
	}

	public int getAnnualDeductible() {
		return annualDeductible;
	}

	public void setMonthWorkingInYear() {
		LocalDate date = LocalDate.now();

		if (date.getYear() == yearJoined) {
			this.monthWorkingInYear = date.getMonthValue() - monthJoined;
		} else {
			this.monthWorkingInYear = 12;
		}

	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3:
	 * 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(int grade, boolean isForeigner) {
		if (grade == 1) {
			monthlySalary = 3000000;
		} else if (grade == 2) {
			monthlySalary = 5000000;
		} else if (grade == 3) {
			monthlySalary = 7000000;
		} else if (isForeigner) {
			monthlySalary = (int) (3000000 * 1.5);
		}
	}
}

class Child {
	private String name;
	private String idNumber;
	private LocalDate birthDate;

	public Child(String name, String idNumber, LocalDate birthDate) {
		this.name = name;
		this.idNumber = idNumber;
		this.birthDate = birthDate;
	}

	public boolean isUnderage() {
		return birthDate != null && birthDate.plusYears(18).isAfter(LocalDate.now());
	}

	public boolean hasValidId() {
		return idNumber != null && !idNumber.isBlank();
	}

	public String getDisplayName() {
		return name == null ? "(Unnamed Child)" : name;
	}
}

class Spouse {
	private String name;
	private String idNumber;

	public Spouse(String name, String idNumber) {
		this.name = name;
		this.idNumber = idNumber;
	}

	public boolean hasValidIdNumber() {
		return idNumber != null && !idNumber.isEmpty();
	}

	public boolean isMarried() {
		return name != null && !name.isEmpty();
	}

	public String getDisplayName() {
		return name == null ? "(Unknown Spouse)" : name;
	}
}