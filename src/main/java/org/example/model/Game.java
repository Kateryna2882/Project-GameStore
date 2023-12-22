package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private int id;
    private String name;
    private String releaseDate;
    private int rating;
    private double cost;
    private String description;

    // Конструктор, геттери, сеттери
}
