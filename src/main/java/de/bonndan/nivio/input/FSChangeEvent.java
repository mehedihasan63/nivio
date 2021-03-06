package de.bonndan.nivio.input;

import org.springframework.context.ApplicationEvent;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

public class FSChangeEvent extends ApplicationEvent {

    private final WatchEvent<?> event;
    private final Path path;

    public FSChangeEvent(Object source, WatchEvent<?> event, Path path) {
        super(source);
        this.event = event;
        this.path = path;
    }

    public WatchEvent<?> getEvent() {
        return event;
    }

    public Path getPath() {
        return path;
    }
}
