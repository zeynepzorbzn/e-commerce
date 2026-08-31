package com.zeynep.eTicaretSitesi.service.paymentMethod;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.PaymentMethod;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.dto.paymentMethod.PaymentMethodInput;
import com.zeynep.eTicaretSitesi.dto.paymentMethod.PaymentMethodResponse;
import com.zeynep.eTicaretSitesi.logic.paymentMethod.PaymentMethodLogic;
import com.zeynep.eTicaretSitesi.mapper.paymentMethod.PaymentMethodMapper;
import com.zeynep.eTicaretSitesi.repo.paymentMethod.PaymentMethodRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService extends BaseService<
        PaymentMethod,
        PaymentMethodInput,
        Long,
        PaymentMethodLogic,
        PaymentMethodMapper,
        PaymentMethodRepository,
        PaymentMethodResponse> {

    public PaymentMethodService(
            PaymentMethodRepository repository,
            PaymentMethodLogic logic,
            PaymentMethodMapper mapper) {

        super(repository, logic, mapper);
    }

    @Override
    public PaymentMethodResponse create(
            PaymentMethodInput input) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user =
                (User) authentication.getPrincipal();

        /*
         * Eğer yeni kart default seçildiyse,
         * kullanıcının eski default kartlarını kaldır.
         */
        if (input.isDefault()) {

            List<PaymentMethod> methods =
                    repository.findByUserId(user.getId());

            methods.forEach(method -> {
                method.setDefault(false);
                repository.save(method);
            });
        }

        PaymentMethod paymentMethod =
                mapper.toEntity(input);

        paymentMethod.setUser(user);

        /*
         * Kullanıcının ilk kartıysa otomatik default yap.
         */
        if (repository.findByUserId(user.getId()).isEmpty()) {
            paymentMethod.setDefault(true);
        }

        PaymentMethod saved =
                repository.save(paymentMethod);

        return mapper.toResponse(saved);
    }

    public List<PaymentMethodResponse> getMyPaymentMethods() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user =
                (User) authentication.getPrincipal();

        return mapper.toResponseList(
                repository.findByUserId(user.getId())
        );
    }
}