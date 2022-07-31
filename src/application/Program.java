package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Sale;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the file path: ");
		String path = sc.nextLine();

		System.out.println();

		List<Sale> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			System.out.println("Five highest average price 2016 sales");

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}

			Comparator<Sale> compAvg = (x, y) -> x.averagePrice().compareTo(y.averagePrice());

			list.stream().filter(s -> s.getYear().equals(2016))
			.sorted(compAvg.reversed())
			.limit(5)
			.forEach(System.out::println);

			String name = "Logan";

			double sum = list.stream().filter(x -> x.getMonth() == 1 || x.getMonth() == 7)
					.filter(x -> x.getSeller().equalsIgnoreCase(name))
					.map(x -> x.getTotal())
					.reduce(0.0, (x, y) -> x + y);

			System.out.println();
			System.out.printf("Total amount sold by seller Logan in months 1 and 7 = %.2f%n", sum);

		}

		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}