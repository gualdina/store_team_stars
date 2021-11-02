package com.bootcamp.store.controller.response;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private Long id;
    private int number;
    private double total;
    private Long userId;
    private List<ProductResponse> productResponseList;
}
