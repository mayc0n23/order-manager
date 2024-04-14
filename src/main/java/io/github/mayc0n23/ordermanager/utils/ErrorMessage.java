package io.github.mayc0n23.ordermanager.utils;

public class ErrorMessage {

    public static final String INVALID_NULL_ID_MESSAGE = "O id do %s não pode ser nulo.";

    public static final String INVALID_SIZE_ID_MESSAGE = "O id do %s deve ser um número entre %d e %d.";

    public static final String INVALID_EMPTY_NAME_MESSAGE = "O nome do usuário não pode ser nulo ou vazio.";

    public static final String INVALID_NAME_LENGTH_MESSAGE = "O nome do usuário não pode ter mais de 45 caracteres.";

    public static final String INVALID_NULL_DATE_MESSAGE = "A data do pedido não pode ser nula.";

    public static final String INVALID_FUTURE_DATE_MESSAGE = "A data do pedido não pode ser maior que a data atual.";

    public static final String INVALID_NULL_VALUE_MESSAGE = "O valor do produto não pode ser nulo.";

    public static final String INVALID_VALUE_MESSAGE = "O valor do produto deve ser maior que 0.";

    public static final String ORDER_NOT_FOUND_MESSAGE = "O pedido %d não foi encontrado.";

    public static final String END_DATE_REQUIRED_MESSAGE = "A data final é obrigatória quando a data inicial é informada.";

    public static final String START_DATE_REQUIRED_MESSAGE = "A data de inicio é obrigatória quando a data final é informada.";

    public static final String INVALID_DATE_FORMAT_MESSAGE = "As datas devem estar no formato yyyy-MM-dd.";

    public static final String INVALID_DATE_RANGE_MESSAGE = "A data final deve ser maior que a data inicial.";

    public static final String READ_FILE_ERROR_MESSAGE = "Falha ao ler o arquivo. Revise o arquivo e tente novamente.";

    public static final String GENERIC_ERROR_MESSAGE = "Ocorreu um erro inesperado. Tente novamente mais tarde.";

    public static final String VALIDATION_ERROR_MESSAGE = "Ocorreu algum erro de validação. Revise os dados e tente novamente.";

    public static final String INVALID_NULL_USER_MESSAGE = "O usuário não pode ser nulo no pedido.";

    public static final String INVALID_EMPTY_PRODUCT_MESSAGE = "O pedido deve conter ao menos um produto.";

}