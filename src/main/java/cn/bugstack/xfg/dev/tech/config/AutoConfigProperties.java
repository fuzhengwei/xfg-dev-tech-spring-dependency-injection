package cn.bugstack.xfg.dev.tech.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "sdk.config", ignoreInvalidFields = true)
public class AutoConfigProperties {

    /** 状态；open = 开启、close 关闭 */
    private boolean enable;
    /** 转发地址 */
    private String apiHost;
    /** 可以申请 sk-*** */
    private String apiSecretKey;

}
