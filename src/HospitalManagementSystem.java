import model.*;
import service.*;
import util.InputUtil;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class HospitalManagementSystem {

    private static final PatientService patientService = new PatientService();
    private static final DoctorService doctorService = new DoctorService();
    private static final AppointmentService appointmentService = new AppointmentService();
    private static final BillingService billingService = new BillingService();
    private static final MedicineService medicineService = new MedicineService();
    private static final PrescriptionService prescriptionService = new PrescriptionService();

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG QUẢN LÝ BỆNH VIỆN ===");
        while (true) {
            showMainMenu();
            int choice = InputUtil.inputInt("Chọn chức năng: ");
            switch (choice) {
                case 1 -> patientMenu();
                case 2 -> doctorMenu();
                case 3 -> appointmentMenu();
                case 4 -> billingMenu();
                case 5 -> medicineMenu();
                case 6 -> prescriptionMenu();

                case 0 -> {
                    System.out.println("Tạm biệt!");
                    System.exit(0);
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("""

                ===== MENU CHÍNH =====
                1. Quản lý Bệnh nhân
                2. Quản lý Bác sĩ
                3. Quản lý Lịch hẹn khám
                4. Quản lý Hóa đơn
                5. Quản lý Thuốc
                6. Quản lý Đơn thuốc
                0. Thoát
                """);
    }

    // ==================== MODULE BỆNH NHÂN ====================
    private static void patientMenu() {
        System.out.println("""
                \n--- QUẢN LÝ BỆNH NHÂN ---
                1. Thêm bệnh nhân
                2. Xem danh sách
                3. Cập nhật
                4. Xóa
                0. Quay lại
                """);
        int choice = InputUtil.inputInt("Chọn: ");
        switch (choice) {
            case 1 -> {
                String id = UUID.randomUUID().toString();
                String name = InputUtil.inputString("Tên: ");
                int age = InputUtil.inputInt("Tuổi: ");
                String gender = InputUtil.inputString("Giới tính: ");
                String diagnosis = InputUtil.inputString("Chẩn đoán: ");
                patientService.addPatient(new Patient(id, name, age, gender, diagnosis));
            }
            case 2 -> patientService.viewAll();
            case 3 -> {
                String id = InputUtil.inputString("Nhập ID bệnh nhân cần cập nhật: ");
                Patient p = patientService.findById(id);
                if (p == null) {
                    System.out.println("Không tìm thấy bệnh nhân.");
                } else {
                    String newName = InputUtil.inputString("Tên mới: ");
                    int newAge = InputUtil.inputInt("Tuổi mới: ");
                    String newGender = InputUtil.inputString("Giới tính mới: ");
                    String newDiagnosis = InputUtil.inputString("Chẩn đoán mới: ");
                    patientService.updatePatient(id, newName, newAge, newGender, newDiagnosis);
                }
            }
            case 4 -> {
                String id = InputUtil.inputString("Nhập ID bệnh nhân cần xóa: ");
                patientService.deletePatient(id);
            }
            case 0 -> {
            }
            default -> System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    // ==================== MODULE BÁC SĨ ====================
    private static void doctorMenu() {
        System.out.println("""
                \n--- QUẢN LÝ BÁC SĨ ---
                1. Thêm bác sĩ
                2. Xem danh sách
                3. Xóa bác sĩ
                0. Quay lại
                """);
        int choice = InputUtil.inputInt("Chọn: ");
        switch (choice) {
            case 1 -> {
                String id = UUID.randomUUID().toString();
                String name = InputUtil.inputString("Tên: ");
                String spec = InputUtil.inputString("Chuyên khoa: ");
                String phone = InputUtil.inputString("Số điện thoại: ");
                doctorService.addDoctor(new Doctor(id, name, spec, phone));
            }
            case 2 -> doctorService.viewAll();
            case 3 -> {
                String id = InputUtil.inputString("Nhập ID bác sĩ cần xóa: ");
                doctorService.deleteDoctor(id);
            }
            case 0 -> {
            }
            default -> System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    // ==================== MODULE KHÁM BỆNH ====================
    private static void appointmentMenu() {
        System.out.println("""
                \n--- QUẢN LÝ LỊCH HẸN ---
                1. Tạo lịch hẹn mới
                2. Xem tất cả lịch hẹn
                3. Hủy lịch hẹn
                0. Quay lại
                """);
        int choice = InputUtil.inputInt("Chọn: ");
        switch (choice) {
            case 1 -> {
                String id = UUID.randomUUID().toString();
                String patientId = InputUtil.inputString("ID bệnh nhân: ");
                String doctorId = InputUtil.inputString("ID bác sĩ: ");
                String date = InputUtil.inputString("Ngày hẹn (dd/MM/yyyy): ");
                appointmentService.addAppointment(new Appointment(id, patientId, doctorId, date));
            }
            case 2 -> appointmentService.viewAll();
            case 3 -> {
                String id = InputUtil.inputString("Nhập ID lịch hẹn cần hủy: ");
                appointmentService.deleteAppointment(id);
            }
            case 0 -> {
            }
            default -> System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    // ==================== MODULE HÓA ĐƠN ====================
    private static void billingMenu() {
        System.out.println("""
                \n--- QUẢN LÝ HÓA ĐƠN ---
                1. Tạo hóa đơn mới
                2. Xem danh sách hóa đơn
                0. Quay lại
                """);
        int choice = InputUtil.inputInt("Chọn: ");
        switch (choice) {
            case 1 -> {
                String id = UUID.randomUUID().toString();
                String patientId = InputUtil.inputString("ID bệnh nhân: ");
                double amount = InputUtil.inputDouble("Số tiền: ");
                String date = InputUtil.inputString("Ngày lập (dd/MM/yyyy): ");
                billingService.addBill(new Bill(id, patientId, amount, date));
            }
            case 2 -> billingService.viewAll();
            case 0 -> {
            }
            default -> System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    // ==================== MODULE THUỐC ====================
    private static void medicineMenu() {
        System.out.println("""
                \n--- QUẢN LÝ THUỐC ---
                1. Thêm thuốc mới
                2. Xem danh sách thuốc
                3. Cập nhật số lượng
                0. Quay lại
                """);
        int choice = InputUtil.inputInt("Chọn: ");
        switch (choice) {
            case 1 -> {
                String id = UUID.randomUUID().toString();
                String name = InputUtil.inputString("Tên thuốc: ");
                int qty = InputUtil.inputInt("Số lượng: ");
                double price = InputUtil.inputDouble("Giá: ");
                medicineService.addMedicine(new Medicine(id, name, qty, price));
            }
            case 2 -> medicineService.viewAll();
            case 3 -> {
                String id = InputUtil.inputString("Nhập ID thuốc cần cập nhật: ");
                int newQty = InputUtil.inputInt("Số lượng mới: ");
                medicineService.updateQuantity(id, newQty);
            }
            case 0 -> {
            }
            default -> System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    // ==================== MODULE ĐƠN THUỐC ====================
    private static void prescriptionMenu() {
        System.out.println("""
                \n--- QUẢN LÝ ĐƠN THUỐC ---
                1. Thêm đơn thuốc mới
                2. Xem danh sách đơn thuốc
                3. Cập nhật đơn thuốc
                4. Xóa đơn thuốc
                5. Tìm kiếm đơn thuốc
                0. Quay lại
                """);

        int choice = InputUtil.inputInt("Chọn: ");
        switch (choice) {
            case 1 -> {
                String id = UUID.randomUUID().toString();
                String examId = InputUtil.inputString("Mã khám: ");
                String medicineName = InputUtil.inputString("Tên thuốc: ");
                String dosage = InputUtil.inputString("Liều lượng: ");
                String usage = InputUtil.inputString("Cách dùng: ");
                String status = InputUtil.inputString("Trạng thái: ");
                int quantity = InputUtil.inputInt("Số lượng: ");
                double price = InputUtil.inputDouble("Giá thuốc: ");
                LocalDate date = InputUtil.inputDate("Ngày tạo (yyyy-MM-dd): ");
                double total = quantity * price;

                Prescription p = new Prescription(id, examId, medicineName, dosage, usage, status, quantity, price,
                        total, date);
                prescriptionService.addPrescription(p);
            }

            case 2 -> prescriptionService.viewAll();

            case 3 -> {
                String id = InputUtil.inputString("Nhập ID đơn thuốc cần cập nhật: ");
                Prescription existing = prescriptionService.findById(id);
                if (existing == null) {
                    System.out.println(" Không tìm thấy đơn thuốc có ID này!");
                    return;
                }

                System.out.println("Nhập thông tin mới (để trống nếu không thay đổi):");
                String newMedicineName = InputUtil.inputString("Tên thuốc mới: ");
                String newDosage = InputUtil.inputString("Liều lượng mới: ");
                String newUsage = InputUtil.inputString("Cách dùng mới: ");
                String newStatus = InputUtil.inputString("Trạng thái mới: ");
                int newQuantity = InputUtil.inputInt("Số lượng mới: ");
                double newPrice = InputUtil.inputDouble("Giá mới: ");
                LocalDate newDate = InputUtil.inputDate("Ngày mới (yyyy-MM-dd): ");

                prescriptionService.updatePrescription(
                        id, newMedicineName, newDosage, newUsage, newStatus, newQuantity, newPrice, newDate);
            }

            case 4 -> {
                String id = InputUtil.inputString("Nhập ID đơn thuốc cần xóa: ");
                prescriptionService.deletePrescription(id);
            }
            case 5 -> {
                System.out.println("""
                        \n--- TÌM KIẾM ĐƠN THUỐC ---
                        1. Theo mã đơn
                        2. Theo mã khám
                        3. Theo tên thuốc
                        0. Quay lại
                        """);
                int opt = InputUtil.inputInt("Chọn: ");
                switch (opt) {
                    case 1 -> {
                        String id = InputUtil.inputString("Nhập mã đơn thuốc: ");
                        Prescription p = prescriptionService.findById(id);
                        if (p == null)
                            System.out.println(" Không tìm thấy đơn thuốc!");
                        else
                            System.out.println(p);
                    }
                    case 2 -> {
                        String examId = InputUtil.inputString("Nhập mã khám: ");
                        var results = prescriptionService.findByExamId(examId);
                        if (results.isEmpty())
                            System.out.println(" Không tìm thấy đơn thuốc!");
                        else
                            results.forEach(System.out::println);
                    }
                    case 3 -> {
                        String name = InputUtil.inputString("Nhập tên thuốc: ");
                        var results = prescriptionService.findByMedicineName(name);
                        if (results.isEmpty())
                            System.out.println("Không tìm thấy đơn thuốc!");
                        else
                            results.forEach(System.out::println);
                    }
                    case 4 -> {
                        LocalDate date = InputUtil.inputDate("Nhập ngày tạo (yyyy-MM-dd): ");
                        var results = prescriptionService.findByDate(date);
                        if (results.isEmpty())
                            System.out.println(" Không có đơn thuốc nào trong ngày này!");
                        else
                            results.forEach(System.out::println);
                    }
                    case 0 -> {
                    }
                    default -> System.out.println(" Lựa chọn không hợp lệ!");
                }
            }

            case 0 -> {
                // Quay lại menu chính
            }

            default -> System.out.println(" Lựa chọn không hợp lệ!");
        }
    }

}
