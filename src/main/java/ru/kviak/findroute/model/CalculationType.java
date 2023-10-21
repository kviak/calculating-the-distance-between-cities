package ru.kviak.findroute.model;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum CalculationType {

    Crowflight,
    Distance_Matrix,
    All
}
