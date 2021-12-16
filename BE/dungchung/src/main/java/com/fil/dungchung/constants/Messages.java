package com.fil.dungchung.constants;

public class Messages {
    public static final String CREATE_SUCCESS = "Tạo mới thành công";
    public static final String CREATE_FAIL = "Tạo mới thất bại";


    public static String createSuccess(String object) {
        return "Tạo mới " + object + " thành công";
    }

    public static String createFailed(String object) {
        return "Tạo mới " + object + " không thành công";
    }

    public static String notFound(String object) {
        return "không tồn tại " + object + " trong dữ liệu";
    }

    public static String getSuccess(String object) {
        return "Lấy " + object + " thành công";
    }

    public static String updateFailed(String object) {
        return "Cập nhật " + object + " không thành công";
    }

    public static String updateSuccess(String object) {
        return "Cập nhật " + object + " thành công";
    }

    public static String deleteSuccess(String object) {
        return "Xóa " + object + " thành công";
    }

    public static String deleteFailed(String object) {
        return "Xóa " + object + " không thành công";
    }
}
