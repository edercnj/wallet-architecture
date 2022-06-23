package com.br.edercnj.credentials.core.ports.outbound;

import com.br.edercnj.credentials.core.domain.entities.ForgotPassord;

public interface ForgotPasswordRepositoryPort {

    void persistForgotPassword(ForgotPassord forgotPassord);

    ForgotPassord findByRecoveryCode(int recoveryCode);
}
