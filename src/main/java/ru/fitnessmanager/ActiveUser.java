package ru.fitnessmanager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

import ru.fitnessmanager.model.User;

public class ActiveUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private final User user;

    public ActiveUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.user = user;
    }

    public static ActiveUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof ActiveUser) ? (ActiveUser) principal : null;
    }

    public static ActiveUser get() {
        ActiveUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
        return get().user.getId();
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
