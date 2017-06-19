package cn.doumi.mvpdemo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class ZhuangbiImage {

    /**
     * id : 551
     * description : 装逼如风常伴我身
     * path :
     * size : 0
     * width : 284
     * height : 199
     * created_at : 2016-01-28 07:22:50
     * updated_at : 2016-01-28 07:23:42
     * user_id : 1
     * permitted_at : 2016-01-27 23:23:42
     * disk : qiniu
     * hotpoint : 918
     * channel : admin
     * upload_id : 1658
     * content :
     * image_url : https://www.zhuangbi.info/uploads/i/2016-01-27-bd6f1e1e31fea64f7f2f33ba31b0b1a2.gif
     * file_size : 1.91 MB
     * upload : {"id":1658,"name":null,"description":"装逼如风常伴我身","disk":"localuploads","path":"i/2016-01-27-bd6f1e1e31fea64f7f2f33ba31b0b1a2.gif","size":2005559,"user_id":1,"created_at":"2016-01-28 07:22:50","updated_at":"2017-04-13 22:23:44","uploadable_id":null,"uploadable_type":null,"url":"https://www.zhuangbi.info/uploads/i/2016-01-27-bd6f1e1e31fea64f7f2f33ba31b0b1a2.gif"}
     */

    @SerializedName("id")
    private int id;
    @SerializedName("description")
    private String description;
    @SerializedName("path")
    private String path;
    @SerializedName("size")
    private int size;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("permitted_at")
    private String permittedAt;
    @SerializedName("disk")
    private String disk;
    @SerializedName("hotpoint")
    private int hotpoint;
    @SerializedName("channel")
    private String channel;
    @SerializedName("upload_id")
    private int uploadId;
    @SerializedName("content")
    private String content;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("file_size")
    private String fileSize;
    /**
     * id : 1658
     * name : null
     * description : 装逼如风常伴我身
     * disk : localuploads
     * path : i/2016-01-27-bd6f1e1e31fea64f7f2f33ba31b0b1a2.gif
     * size : 2005559
     * user_id : 1
     * created_at : 2016-01-28 07:22:50
     * updated_at : 2017-04-13 22:23:44
     * uploadable_id : null
     * uploadable_type : null
     * url : https://www.zhuangbi.info/uploads/i/2016-01-27-bd6f1e1e31fea64f7f2f33ba31b0b1a2.gif
     */

    @SerializedName("upload")
    private UploadEntity upload;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPermittedAt() {
        return permittedAt;
    }

    public void setPermittedAt(String permittedAt) {
        this.permittedAt = permittedAt;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public int getHotpoint() {
        return hotpoint;
    }

    public void setHotpoint(int hotpoint) {
        this.hotpoint = hotpoint;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getUploadId() {
        return uploadId;
    }

    public void setUploadId(int uploadId) {
        this.uploadId = uploadId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public UploadEntity getUpload() {
        return upload;
    }

    public void setUpload(UploadEntity upload) {
        this.upload = upload;
    }

    public static class UploadEntity {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private Object name;
        @SerializedName("description")
        private String description;
        @SerializedName("disk")
        private String disk;
        @SerializedName("path")
        private String path;
        @SerializedName("size")
        private int size;
        @SerializedName("user_id")
        private int userId;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("uploadable_id")
        private Object uploadableId;
        @SerializedName("uploadable_type")
        private Object uploadableType;
        @SerializedName("url")
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDisk() {
            return disk;
        }

        public void setDisk(String disk) {
            this.disk = disk;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Object getUploadableId() {
            return uploadableId;
        }

        public void setUploadableId(Object uploadableId) {
            this.uploadableId = uploadableId;
        }

        public Object getUploadableType() {
            return uploadableType;
        }

        public void setUploadableType(Object uploadableType) {
            this.uploadableType = uploadableType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
