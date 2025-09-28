package ru.yandex.practicum.filmorate.storage.friends;

import java.util.Set;

public interface FriendsStorage {
    void addFriends(Long userId, Long friendId);

    void removeFriends(Long userId, Long friendId);

    Set<Long> getFriends(Long userId);

    Set<Long> getCommonFriends(Long userId, Long otherId);
}
