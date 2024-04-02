package org.amine.hnms.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationPerformance {
    private Integer CTR;
    private Integer CVR;
    private Integer clicks;
    private Integer impressions;
    private Integer conversions;
}
