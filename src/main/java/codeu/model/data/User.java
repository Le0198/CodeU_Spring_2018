// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.model.data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/** Class representing a registered user. */
public class User {
  private final UUID id;
  private final String name;
  private final String passwordHash;
  private final Instant creation;
  private ArrayList<String> friends;
  private String aboutMe;

  /**
   * Constructs a new User.
   *
   * @param id the ID of this User
   * @param name the username of this User
   * @param passwordHash the password hash of this User
   * @param creation the creation time of this User
   */
  public User(UUID id, String name, String passwordHash, Instant creation) {
    this.id = id;
    this.name = name;
    this.passwordHash = passwordHash;
    this.creation = creation;
    this.aboutMe = "";
    this.friends = new ArrayList<>();
  }

  /** Returns the ID of this User. */
  public UUID getId() {
    return id;
  }

  /** Returns the username of this User. */
  public String getName() {
    return name;
  }

  /** Returns the password hash of this User. */
  public String getPasswordHash() {
    return passwordHash;
  }

  /** Returns the creation time of this User. */
  public Instant getCreationTime() {
    return creation;
  }

  /** Returns the about me of the User. */
  public String getAboutMe() {
    if (aboutMe == null) return "";
    else return aboutMe;
  }

  /** Changes the about me content of the User. */
  public void setAboutMe(String aboutMe) {
    this.aboutMe = aboutMe;
  }

  public void setFriends(String friends) {
    if (friends != null) {
      friends = friends.replaceAll("\\[", "").replaceAll("\\]", "");
      this.friends = new ArrayList<String>(Arrays.asList(friends.split(",")));
    }
  }

  /** Get the list of friends the User has */
  public ArrayList<String> getFriends() {
    return friends;
  }

  public boolean addFriend(String friendName) {
    if (!friends.contains(friendName)) {
      friends.add(friendName);
      return true;
    }
    return false;
  }

  public boolean removeFriend(String friendName) {
    if (friends.contains(friendName)) {
      friends.remove(friendName);
      return true;
    }
    return false;
  }
}
