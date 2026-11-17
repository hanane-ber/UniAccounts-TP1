package main;

import audit.AuditLog;
import model.StudentAccount;
import prototype.AccountTemplateRegistry;
import repository.AccountRepository;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AccountRepository repo = AccountRepository.getInstance();
        AuditLog audit = AuditLog.getInstance();

        System.out.println("=== 1. Test du Pattern Builder ===");
        StudentAccount student1 = new StudentAccount.StudentAccountBuilder(
                "2026001", "Hanane Berrached", "hanane@univ-eloued.dz")
                .level("L2")
                .major("Informatique")
                .scholarship(true)
                .courses(Arrays.asList("Algorithmique", "POO", "BDD"))
                .build();

        if (repo.addAccount(student1)) {
            audit.log("Compte cree et ajoute avec succes: " + student1.getId());
        }

        System.out.println("\n=== 2. Test du Pattern Prototype ===");
        StudentAccount l2InfoTemplate = new StudentAccount.StudentAccountBuilder(
                "TEMPLATE_ID", "Template Name", "template@univ.dz")
                .level("L2")
                .major("Informatique")
                .courses(Arrays.asList("Algorithmique", "POO", "SE", "Reseaux"))
                .build();

        AccountTemplateRegistry registry = new AccountTemplateRegistry();
        registry.registerTemplate("L2_INFO", l2InfoTemplate);

        StudentAccount student2 = registry.createFromTemplate(
                "L2_INFO", "2026002", "Ahmed Ben Ali", "ahmed@univ-eloued.dz");
        StudentAccount student3 = registry.createFromTemplate(
                "L2_INFO", "2026003", "Sara Mahmoud", "sara@univ-eloued.dz");

        student2.getCourses().add("Anglais");

        if (repo.addAccount(student2)) audit.log("Compte clone et ajoute: " + student2.getId());
        if (repo.addAccount(student3)) audit.log("Compte clone et ajoute: " + student3.getId());

        System.out.println("\n=== 3. Test du Singleton (Unicite du Repository) ===");
        repo.addAccount(student1); // Tentative d'ajout d'un ID en double

        System.out.println("\n--- Liste des comptes dans le Repository ---");
        for (StudentAccount acc : repo.getAllAccounts()) {
            System.out.println(acc + " | Cours: " + acc.getCourses());
        }
    }
}