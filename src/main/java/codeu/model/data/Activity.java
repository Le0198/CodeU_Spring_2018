package codeu.model.data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
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

    /** Returns the ID of this Activity. */
    public UUID getId() {
        return id;
    }

    /** Returns the title of this Activity. */
    public String getContent() {

        return content;
    }

    /** Returns the creation time of this Activity. */
    public Instant getCreationTime() {
        return creation;
    }

    /** Returns the creation time of this Activity as a formatted string. */
    public String getCreationTimeAsString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedDateTime( FormatStyle.MEDIUM )
                        .withLocale( Locale.US)
                        .withZone( ZoneId.systemDefault() );
        String output = formatter.format( creation );
        return output;
    }
}
