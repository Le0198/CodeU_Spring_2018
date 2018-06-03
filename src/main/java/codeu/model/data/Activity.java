package codeu.model.data;

import java.time.Instant;
import java.util.UUID;

public class Activity {
    public final UUID id;
    public final Instant creation;
    public final String content;

    /**
     * Constructs a new Activity.
     *
     * @param id the ID of this Activity
     * @param content the text content of this Activity
     * @param creation the creation time of this Activity
     */
    public Activity(UUID id, String content, Instant creation) {
        this.id = id;
        this.creation = creation;
        this.content = content;
    }

    /** Returns the ID of this Conversation. */
    public UUID getId() {
        return id;
    }

    /** Returns the title of this Conversation. */
    public String getContent() {
        return content;
    }

    /** Returns the creation time of this Conversation. */
    public Instant getCreationTime() {
        return creation;
    }
}
