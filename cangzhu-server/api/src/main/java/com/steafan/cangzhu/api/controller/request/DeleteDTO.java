package com.steafan.cangzhu.api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class DeleteDTO {
    @NotNull
    private List<Integer> ids;
}
