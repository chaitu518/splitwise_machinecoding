package com.srt.splitwise.service;

import com.srt.splitwise.Exceptions.GroupRelatedException;
import com.srt.splitwise.Models.Expense;
import com.srt.splitwise.Models.Group;
import com.srt.splitwise.repo.InMemoryExpenseRepo;
import com.srt.splitwise.repo.InMemoryGroupRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private InMemoryExpenseRepo expenseRepo;
    private InMemoryGroupRepo groupRepo;
    public ExpenseServiceImpl(InMemoryExpenseRepo expenseRepo,InMemoryGroupRepo groupRepo) {
        this.expenseRepo = expenseRepo;
        this.groupRepo = groupRepo;
    }
    @Override
    public Expense addExpense(Expense expense) throws GroupRelatedException {
        Group group = groupRepo.getGroup(expense.getGroupId());
        if(group == null) {
            throw new GroupRelatedException("Group not found");
        }
        HashSet<Long> hs = new HashSet<>();
        for(Long memberId : group.getMembers()) {
            hs.add(memberId);
        }
        for(Long memberId : expense.getParticipantsIds()) {
            if(!hs.contains(memberId)) {
                throw new GroupRelatedException("User not found in group");
            }
        }
        Expense expense1 = expenseRepo.add(expense);
        List<Long> groupExpenses = group.getExpensesIds();
        if(groupExpenses == null) {
            groupExpenses = new ArrayList<Long>();
        }
        groupExpenses.add(expense1.getId());
        group.setExpensesIds(groupExpenses);

        groupRepo.updateGroup(group.getId(), group);
        return expense1;
    }


    public List<Long> getAllExpenses(long groupId) throws GroupRelatedException {
        if(groupRepo.getGroup(groupId) == null) {
            throw new GroupRelatedException("Group with given id:"+groupId+"not present");
        }
        return groupRepo.getExpenses(groupId);
    }


}
