package org.example;

import java.time.Instant;

public class Task {
    private long id;
    private String title;
    private String description;
    private Instant time;
    private long difficult;
    private boolean done;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Instant getTime() {
        return time;
    }

    public long getDifficult() {
        return difficult;
    }

    public boolean isDone() {
        return done;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", difficult=" + difficult +
                ", done=" + done +
                '}';
    }
}
