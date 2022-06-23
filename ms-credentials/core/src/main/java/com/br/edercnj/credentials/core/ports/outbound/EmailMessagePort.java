package com.br.edercnj.credentials.core.ports.outbound;

import com.br.edercnj.credentials.core.domain.entities.ForgotPassord;

public interface EmailMessagePort {

    void sendForgotPasswordMessage(ForgotPassord forgotPassord);
}
