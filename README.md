- ### Thành viên nhóm

        | Họ và tên          | MSSV    |
        | ------------------ | ------- |
        | Đặng Thành Duy Đan | 2310615 |
        | Trần Tiến Đạt      | 2310707 |

- ### Public URL: https://cnpmnc-lab-a9wk.onrender.com/

- ### Hướng dẫn chạy dự án

- ### Trả lời câu hỏi lý thuyết Lab
  1. **Tại sao Database lại chặn thao tác Insert trùng ID và báo lỗi `UNIQUE constraint failed`?**

     Hệ quản trị cơ sở dữ liệu chặn thao tác này vì trường `id` được định nghĩa là **Khóa chính (PRIMARY KEY)**. Ở mức hệ thống, Khóa chính tự động áp đặt ràng buộc **duy nhất (UNIQUE)**. Việc cơ chế Storage Engine bắt lỗi và rollback giao dịch này nhằm 2 mục đích kỹ thuật:
     1. **Bảo vệ Tính toàn vẹn thực thể (Entity Integrity):** Đảm bảo mỗi bản ghi (sinh viên) là định danh.
     2. **Ngăn chặn Nhập nhằng dữ liệu (Data Ambiguity):** Nếu cho phép trùng ID, các câu lệnh DML (`UPDATE`, `DELETE`) sau này sẽ không thể xác định chính xác mục tiêu, dẫn đến nguy cơ sửa/xóa nhầm dữ liệu diện rộng.

  2. **Thử Insert sinh viên bỏ trống cột `name` (để NULL). Database có báo lỗi không? Sự thiếu chặt chẽ này ảnh hưởng gì khi code Java?**

     SQLite không báo lỗi và vẫn cho phép thành công. Nguyên nhân là do câu lệnh DDL khởi tạo bảng `students` thiếu ràng buộc `NOT NULL` ở cột `name` (`name TEXT`). Khi Spring Data JPA thực hiện ORM (Object-Relational Mapping) để kéo bản ghi này lên bộ nhớ, thuộc tính `name` của đối tượng `Student` sẽ bị gán thành `null`. Bất kỳ lời gọi hàm nào can thiệp vào thuộc tính này đều sẽ kích hoạt ngoại lệ **`NullPointerException` (NPE)** tại thời điểm Runtime (Runtime Exception), làm crash luồng thực thi và trả về lỗi HTTP 500 Internal Server Error cho Client.

- ### Screenshot module Lab 4
