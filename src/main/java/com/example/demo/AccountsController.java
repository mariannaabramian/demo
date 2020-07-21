package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountsController {
    @Autowired
    private AccountsRepository accounts;

    @Autowired
    private TasksRepository tasks;

    @GetMapping("/user/{id}/profile")
    public String userProfile(@PathVariable int id, Model model) {
        Account account = accounts.findById(id).get();
        List<Task> foundTasks = tasks.findByAccount(account);

        model.addAttribute("account", account);
        model.addAttribute("tasks", foundTasks);

        return "profile";
    }

    @GetMapping("/user/all")
    public String listAccounts(Model model) {
        ArrayList<Account> allAccounts = new ArrayList<>();
        accounts.findAll().forEach(allAccounts::add);
//        accounts.findAll().forEach(account -> {
//            allAccounts.add(account);
//        });

        model.addAttribute("accounts", allAccounts);
        return "accounts";
    }

    @GetMapping("/all/create")
    public String createAll(Model model) {
        for (int i = 0; i < 10; ++i) {
            Account account = new Account("user-" + i);
            accounts.save(account);

            for (int j = 0; j < 10; ++j) {
                Task task = new Task("task-" + j, account);
                tasks.save(task);
            }
        }

        Account someAccount = accounts.findAll().iterator().next();
        model.addAttribute("account", someAccount);
        model.addAttribute("tasks", tasks.findByAccount(someAccount));

        return "profile";
    }
}
