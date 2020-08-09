package deveficiente.mercadolivre.commons.api.validators;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<?> targetClass;
    private String field;

    @Override
    public void initialize(Unique annotation) {
        targetClass = annotation.targetClass();
        field = annotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Boolean> query = criteriaBuilder.createQuery(Boolean.class);
        query.from(targetClass);
        query.select(criteriaBuilder.literal(true));
        Subquery subquery = query.subquery(targetClass);
        Root subRootEntity = subquery.from(targetClass);
        subquery.select(subRootEntity);
        subquery.where(criteriaBuilder.equal(subRootEntity.get(field), value));
        query.where(criteriaBuilder.exists(subquery));
        return entityManager.createQuery(query)
                .getResultList()
                .isEmpty();
    }
}
