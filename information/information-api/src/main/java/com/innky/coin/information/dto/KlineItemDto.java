package com.innky.coin.information.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author innnky
 * @date 2022/9/26 10:06
 */
@Data
public class KlineItemDto implements Serializable {

	BigDecimal open;

	BigDecimal close;

	BigDecimal high;

	BigDecimal low;

	BigDecimal amount;

}
