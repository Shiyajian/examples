package entity.response;

import lombok.Data;
import lombok.ToString;

/**
 * @author shiyajian
 * create: 2018-11-25
 */
@Data
@ToString
public class ProviderResponse {

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 响应信息
     */
    private String responseMsg;

    /**
     * 文件内容大小
     */
    private Long contentLength;

    /**
     * 成功标记
     */
    private boolean succeed;
}
