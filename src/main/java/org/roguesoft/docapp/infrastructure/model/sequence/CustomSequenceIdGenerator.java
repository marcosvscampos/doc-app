package org.roguesoft.docapp.infrastructure.model.sequence;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.spi.TypeConfiguration;
import org.roguesoft.docapp.infrastructure.utils.RandomValueGenerator;
import org.roguesoft.docapp.infrastructure.utils.RandomNumberGenerator;
import org.roguesoft.docapp.infrastructure.utils.RandomStringGenerator;

import java.io.Serializable;
import java.util.Properties;

public class CustomSequenceIdGenerator extends SequenceStyleGenerator {

    public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
    public static final String VALUE_PREFIX_DEFAULT = "";
    private String valuePrefix;

    private final RandomValueGenerator stringValueGenerator;

    private final RandomValueGenerator numberValueGenerator;

    public CustomSequenceIdGenerator() {
        this.stringValueGenerator = new RandomStringGenerator();
        this.numberValueGenerator = new RandomNumberGenerator();
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object object) throws HibernateException {


        return valuePrefix + "-" + numberValueGenerator.generate(8) + "-" + stringValueGenerator.generate(3);
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(new TypeConfiguration().getBasicTypeRegistry().getRegisteredType(Long.class), params, serviceRegistry);
        valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER,
                params, VALUE_PREFIX_DEFAULT);
    }

}
