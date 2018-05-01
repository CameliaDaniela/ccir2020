package ccir2020MV.startApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ccir2020MV.exceptions.InvalidFormatException;

import ccir2020MV.model.base.Activity;
import ccir2020MV.model.base.Contact;
import ccir2020MV.model.base.User;
import ccir2020MV.model.repository.classes.RepositoryActivityFile;
import ccir2020MV.model.repository.classes.RepositoryContactFile;
import ccir2020MV.model.repository.classes.RepositoryUserFile;
import ccir2020MV.model.repository.interfaces.RepositoryActivity;
import ccir2020MV.model.repository.interfaces.RepositoryContact;
import ccir2020MV.model.repository.interfaces.RepositoryUser;

//functionalitati
//i.	 adaugarea de contacte (nume, adresa, numar de telefon, adresa email);
//ii.	 programarea unor activitati (denumire, descriere, data, locul, ora inceput, durata, contacte).
//iii.	 generarea unui raport cu activitatile pe care le are utilizatorul (nume, user, parola) la o anumita data, ordonate dupa ora de inceput.

public class MainClass {

	public static void main(String[] args) {
		BufferedReader in = null;
		try {
			RepositoryContactFile contactRep = new RepositoryContactFile();
			RepositoryUserFile userRep = new RepositoryUserFile();
			RepositoryActivityFile activityRep = new RepositoryActivityFile(
					contactRep);

			User user = null;
			in = new BufferedReader(new InputStreamReader(System.in));
			while (user == null) {
				System.out.printf("Enter username: ");
				String u = in.readLine();
				System.out.printf("Enter password: ");
				String p = in.readLine();

				user = userRep.getByUsername(u);
				if (!user.isPassword(p))
					user = null;
			}

			int chosen = 0;
			while (chosen != 4) {
				printMenu();
				chosen = Integer.parseInt(in.readLine());
				try {
					switch (chosen) {
					case 1:
						citireContact(in,contactRep);
						break;
					case 2:
						adaugActivitate(activityRep, contactRep, in, user);
						break;
					case 3:
						afisActivitate(activityRep, in, user);
						break;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			// List<Activity> act =
			// activityRep.activitiesByName(user.getName());
			// for(Activity a : act)
			// System.out.println(a.toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Program over and out\n");
	}

	private static void afisActivitate(RepositoryActivityFile activityRep,
			BufferedReader in, User user) {
		try {
			System.out.printf("Afisare Activitate: \n");
			System.out.printf("Data(format: mm/dd/yyyy): ");
			String dateS = in.readLine();
			Calendar c = Calendar.getInstance();
			c.set(Integer.parseInt(dateS.split("/")[2]),
					Integer.parseInt(dateS.split("/")[0]) - 1,
					Integer.parseInt(dateS.split("/")[1]));
			Date d = c.getTime();

			System.out.println("Activitatile din ziua " + d.toString() + ": ");

			List<Activity> act = activityRep
					.activitiesOnDate(user.getName(), d);
			for (Activity a : act) {
				System.out.printf("%s - %s: %s with: ", a.getStart()
						.toString(), a.getDuration().toString(), a
						.getDescription());
				for (Contact con : a.getContacts())
					System.out.printf("%s, ", con.getName());
				System.out.println();
			}
		} catch (IOException e) {
			System.out.printf("Eroare de citire: %s\n" + e.getMessage());
		}
	}

	private static void adaugActivitate(RepositoryActivityFile activityRep,
			RepositoryContactFile contactRep, BufferedReader in, User user) {
		try {
			System.out.printf("Adauga Activitate: \n");
			System.out.printf("Descriere: ");
			String description = in.readLine();
			System.out.printf("Start Date(format: mm/dd/yyyy): ");
			String dateS = in.readLine();
			System.out.printf("Start Time(hh:mm): ");
			String timeS = in.readLine();
			Calendar c = Calendar.getInstance();
			c.set(Integer.parseInt(dateS.split("/")[2]),
					Integer.parseInt(dateS.split("/")[0]) - 1,
					Integer.parseInt(dateS.split("/")[1]),
					Integer.parseInt(timeS.split(":")[0]),
					Integer.parseInt(timeS.split(":")[1]),
					0);
			Date start = c.getTime();

			System.out.printf("End Date(format: mm/dd/yyyy): ");
			String dateE = in.readLine();
			System.out.printf("End Time(hh:mm): ");
			String timeE = in.readLine();
			
			c.set(Integer.parseInt(dateE.split("/")[2]),
					Integer.parseInt(dateE.split("/")[0]) - 1,
					Integer.parseInt(dateE.split("/")[1]),
					Integer.parseInt(timeE.split(":")[0]),
					Integer.parseInt(timeE.split(":")[1]),
					0);
			Date end = c.getTime();

			Activity act = new Activity(user.getName(), start, end,
					new LinkedList<Contact>(), description);

			boolean adaugat=activityRep.addActivity(act);
			activityRep.saveActivities();
			if(adaugat)
				System.out.printf("S-a adaugat cu succes\n");
			else
				System.out.println("err");
		} catch (IOException e) {
			System.out.printf("Eroare de citire: %s\n" + e.getMessage());
		}

	}
	private  static void citireContact(BufferedReader in,RepositoryContactFile repo)
	{
		System.out.printf("Adauga Contact: \n");
		System.out.printf("Nume: ");
		try {
			String name = in.readLine();

		System.out.printf("Adresa: ");
		String adress = in.readLine();
		System.out.printf("Numar de telefon: ");
		String telefon = in.readLine();
		repo.adaugContact(name,adress,telefon);

		} catch (IOException e) {
			System.out.printf("Eroare de citire: %s\n" + e.getMessage());
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
	}


	private static void printMenu() {
		System.out.printf("Please choose option:\n");
		System.out.printf("1. Adauga contact\n");
		System.out.printf("2. Adauga activitate\n");
		System.out.printf("3. Afisare activitati din data de...\n");
		System.out.printf("4. Exit\n");
		System.out.printf("Alege: ");
	}
}
