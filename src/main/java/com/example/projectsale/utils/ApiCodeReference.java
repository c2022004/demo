package com.example.projectsale.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Slf4j
public class ApiCodeReference {
    private ApiCodeReference() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    private static final String UTILITY_CLASS = "Utility class";

    public static class OnBoarding {
        private OnBoarding() {
            throw new IllegalStateException(UTILITY_CLASS);
        }
        public static final String SYSTEM_ERROR_MSG = "System error!";
        public static final String SYSTEM_ERROR_CODE = "099";
        public static final String DATABASE_ERROR_MSG = "Database error!";
        public static final String DATABASE_ERROR_CODE = "100";
        private static final String PREFIX = "ONB";
        public static final String CAN_NOT_LINK_ACCOUNT_ERROR = PREFIX.concat("002");
        public static final String SIGNATURE_INVALID_ERROR = PREFIX.concat("003");
        public static final String CAN_NOT_INIT_TRANSACTION_MESSAGE = "Initialization transaction failed";
        public static final String CAN_NOT_LINK_ACCOUNT_MESSAGE = "Link account failed";
        public static final String SIGNATURE_INVALID_MESSAGE = "Signature invalid";
        public static final String VALIDATE_PHONE_ERROR = PREFIX.concat("004");
        public static final String VALIDATE_PHONE_MESSAGE = "Error Can not validate phone";
        public static final String CREATE_MPIN_ERROR = PREFIX.concat("005");
        public static final String CREATE_MPIN_MESSAGE = "Error can not create mPIN";
        public static final String CREATE_BIOMETRIC_ERROR = PREFIX.concat("006");
        public static final String CREATE_BIOMETRIC_MESSAGE = "Error can not create Biometric";
        public static final String GET_CHALLENGE_CODE_ERROR = PREFIX.concat("007");
        public static final String GET_CHALLENGE_CODE_MESSAGE = "Error can not get challenge code";
        public static final String GET_TOKEN_ERROR = PREFIX.concat("001");
        public static final String GET_TOKEN_MESSAGE = "Error can not get Token";
        public static final String DATA_INVALID_ERROR = PREFIX.concat("008");
        public static final String DATA_INVALID_MESSAGE = "Data Invalid";

        public static final String DATA_INVALID_MESSAGE_WITH_FORMAT = "Data Fields {%s} Invalid";
        public static final String DATA_CORE_NOT_FOUND_MESSAGE = "Link account failed due to clientno and userrole not found";

        public static final String DATA_EXISTED_MESSAGE_WITH_FORMAT = "Data Fields {%s} Existed";


        public static final String VERIFY_TOKEN_ERROR = PREFIX.concat("009");
        public static final String VERIFY_TOKEN_MESSAGE = "Verify token failed";
        public static final String STAGE_INVALID_ERROR = PREFIX.concat("010");
        public static final String STAGE_INVALID_MESSAGE = "Stage invalid";

        public static final String TRANSACTION_ID_EXPIRE = PREFIX.concat("011");

        public static final String TRANSACTION_ID_EXPIRE_MESSAGE = "Transaction ID expired";

        public static final String REGISTER_USER_ERROR_CODE = PREFIX.concat("012");
        public static final String REGISTER_USER_ERROR_MESSAGE = "Error while register user";

        public static final String REGISTER_USER_FAILED_CODE = PREFIX.concat("013");
        public static final String REGISTER_USER_FAILED_MESSAGE = "Register user failed";
        public static final String CHECK_REGISTER_USER_FAILED_CODE = PREFIX.concat("014");
        public static final String CHECK_REGISTER_USER_FAILED_MESSAGE = "Error while check Register User Info";

        public static final String CHECK_REGISTER_USER_DATA_NOT_MATCH = PREFIX.concat("015");
        public static final String CHECK_REGISTER_USER_DATA_NOT_MATCH_MESSAGE = "Thông tin chưa phù hợp để đăng ký trực tuyến. Quý khách vui lòng liên hệ CN/PGD HDBank gần nhất để được hỗ trợ";
        public static final String DEVICE_ID_LINKED_ERROR = PREFIX.concat("016");
        public static final String DEVICE_ID_LINKED_MESSAGE = "Thiết bị đã đăng ký quá số lần quy định. Quý khách vui lòng thử lại sau";

        public static final String MAX_COUNT_LIMIT = PREFIX.concat("017");
        public static final String MAX_COUNT_LIMIT_MESSAGE = "Parner user Read MAX COUNT LIMIT";

    }

    public static class DTCErrorCode {
        public static final String DTC_CODE_RESULT_NOT_EXIST = "00";
        public static final String DTC_CODE_RESULT_ACCOUNT_CLOSED = "1404";
        public static final String DTC_CODE_RESULT_ACCOUNT_CANCEL = "1401";
        public static final String DTC_CODE_RESULT_ACCOUNT_NOT_ACTIVE = "24";

        public static final String DTC_CODE_RESULT_INVALID = "05";

        public static final List<String> LIST_DTC_CODE_EXIST = Arrays.asList("1401", "1402", "1439");

    }

    public static class UnLinkAccount {
        private UnLinkAccount() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        private static final String PREFIX = "UNL";
        public static final String UN_LINK_ACCOUNT_ERROR = PREFIX.concat("001");
        public static final String UN_LINK_ACCOUNT_MESSAGE = "Lỗi xảy ra khi hủy liên kết";

        public static final String VOUCHER_IS_ACTIVE_OR_PAYMENT_ERROR = PREFIX.concat("002");
        public static final String VOUCHER_IS_ACTIVE_OR_PAYMENT_MESSAGE = "Tài khoản có giao dich/Voucher chưa hoàn thành";
        
        public static final String LOAN_NOT_PAYMENT_ALL_ERROR = PREFIX.concat("003");
        public static final String LOAN_NOT_PAYMENT_ALL_MESSAGE = "Tài khoản chưa tất toán khoản vay";


    }
    
    public static class OverDraft {
        private OverDraft() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        private static final String PREFIX = "OD";
        public static final String OVER_DRAFT_HAS_BEEN_REGISTERED = PREFIX.concat("001");
        public static final String OVER_DRAFT_HAS_BEEN_REGISTERED_MESSAGE = "KH đã đăng ký";
        public static final String LINK_ACCOUNT_NOT_FOUND = PREFIX.concat("002");
        public static final String LINK_ACCOUNT_NOT_FOUND_MESSAGE = "Không tìm thấy liên kết tài khoản";
        public static final String OD_NOT_FOUND = PREFIX.concat("003");
        public static final String OD_NOT_FOUND_MESSAGE = "Không tìm thấy hồ sơ đăng ký hạn mức";
        public static final String SYSTEM_ERROR_MSG = "System error!";
        public static final String SYSTEM_ERROR_CODE = "099";
        public static final String DATABASE_ERROR_MSG = "Database error!";
        public static final String DATABASE_ERROR_CODE = "100";
        public static final String DATA_INVALID_ERROR = PREFIX.concat("008");
        public static final String DATA_INVALID_MESSAGE = "Data Invalid";

    }

    @Getter
    public enum OverDraftErrorType {
        REGISTERED(OverDraft.PREFIX.concat("001"), "Over draft has been registered."),
        LINK_ACCOUNT_NOT_FOUND(OverDraft.PREFIX.concat("002"), "Not found linking account information."),
        LOCAL_CREDIT_NOT_FOUND(OverDraft.PREFIX.concat("003"), "Not found local credit account information."),
        REJECTED_BY_APPROVAL(OverDraft.PREFIX.concat("004"), "Over draft has been rejected by approval."),
        REJECTED_BY_CIC_SYSTEM(OverDraft.PREFIX.concat("005"), "Over draft has been rejected by CIC system."),
        TEMPORARY_LOCK_BY_NO_TRANSACTIONS(OverDraft.PREFIX.concat("006"), "Over draft has been temporary locked cause no transactions."),
        TEMPORARY_LOCK_BY_NO_PREV_TRANSACTIONS(OverDraft.PREFIX.concat("007"), "Over draft has been temporary locked cause no previous transactions."),
        LOCKED_DUE_TO_DEBT_UNDER_30_DAYS(OverDraft.PREFIX.concat("008"), "Over draft has been locked due to debt under 30 days."),
        LOCKED_DUE_TO_DEBT_OVER_30_DAYS(OverDraft.PREFIX.concat("009"), "Over draft has been locked due to debt over 30 days."),
        LOCKED_BY_OVER_DUE_DEBT_GROUP_2_OR_HIGHER(OverDraft.PREFIX.concat("010"), "Over draft has been locked by over ue debt group 2 or higher."),
        NOT_ALLOW_CREATE_OD(OverDraft.PREFIX.concat("011"), "Temporary not allow to create OD."),
        NOT_ALLOW_CREATE_OD_BY_PROCESSING(OverDraft.PREFIX.concat("012"), "OD in processing.");

        private final String code;
        private final String message;

        OverDraftErrorType(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}