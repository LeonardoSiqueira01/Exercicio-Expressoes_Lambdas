package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		List<Employee> list = new ArrayList<>();
		System.out.print("Enter file full path: ");
		String path = sc.next();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			System.out.print("Enter Salary:");
			double salary = sc.nextDouble();
			Comparator<String> compare = (v1, v2) -> v1.toUpperCase().compareTo(v2.toUpperCase());
			Stream<String> sm1 = list.stream().filter(x -> x.getSalary() >= salary).map(p -> p.getEmail())
					.sorted(compare);
			System.out.println("Email of people whose salary is more than 2000.00:");
			sm1.forEach(System.out::println);
			Double sum = list.stream().filter(x -> x.getName().charAt(0) == 'M').map(x -> x.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			System.out.println("Sum of salary of people whose name Starts with 'M': U$" + String.format("%.2f", sum));

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
