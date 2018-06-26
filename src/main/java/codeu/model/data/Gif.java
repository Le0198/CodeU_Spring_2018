package codeu.model.data;

import java.time.Instant;
import java.util.UUID;

public class Gif {
    public final UUID id;
    public final Instant creation;
    public final String url;
    public final String tag;

    /**
     * Constructs a new gif.
     *
     * @param id the ID of this gif
     * @param url the url of this gif
     * @param tag the tag of this gif
     * @param creation the creation time of this gif
     */
    public Gif(UUID id, String url, String tag, Instant creation) {
        this.id = id;
        this.creation = creation;
        this.tag = tag;
        this.url = url;
    }

    /** Returns the ID of this gif. */
    public UUID getId() {
        return id;
    }

    /** Returns the url of the gif. */
    public String getURL() {
        return url;
    }
    
    /** Returns the tag of this gif. */
    public String getTag() {
        return tag;
    }

    /** Returns the creation time of this gif. */
    public Instant getCreationTime() {
        return creation;
    }
}
