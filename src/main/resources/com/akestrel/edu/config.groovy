package com.akestrel.edu

import com.akestrel.edu.SomePersonService
import com.akestrel.edu.ValidateAspect
import org.springframework.aop.framework.ProxyFactoryBean
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

/**
 * Created by Aleksey on 18.05.2015.
 */
beans {



    xmlns([aop:'http://www.springframework.org/schema/aop'])
    aop.config {
        aop.aspect('id': 'validateAspect', 'ref': 'validateAspectBean') {
            aop.pointcut('id': 'validatePointcut',
                    'expression': 'execution(* *(@org.springframework.validation.annotation.Validated (*)))')
            aop.before('pointcut-ref': 'validatePointcut', 'method': 'validate')
        }
    }

    validator (LocalValidatorFactoryBean) {}

    validateAspectBean(ValidateAspect) {
        validator = ref (validator)
    }

    personService (SomePersonService) {}


}