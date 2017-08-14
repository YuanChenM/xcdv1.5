package com.msk.common.data.jpa.condition;

import java.io.Serializable;

import javax.persistence.criteria.Expression;

import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.hibernate.jpa.criteria.Renderable;
import org.hibernate.jpa.criteria.compile.RenderingContext;
import org.hibernate.jpa.criteria.expression.LiteralExpression;
import org.hibernate.jpa.criteria.predicate.LikePredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LikeNvarcharPredicate extends LikePredicate implements Serializable{
    /** serialVersionUID */
    private static final long serialVersionUID = 9099569159635250390L;

     private static Logger logger = LoggerFactory.getLogger(LikeNvarcharPredicate.class);

    public LikeNvarcharPredicate(CriteriaBuilderImpl criteriaBuilder, Expression<String> matchExpression,
                                 Expression<String> pattern, char escapeCharacter) {
        super(criteriaBuilder, matchExpression, pattern, new LiteralExpression<Character>(criteriaBuilder,
                escapeCharacter));
    }

    @Override
    public String render(RenderingContext renderingContext) {
        String operator;
        if (isNegated()) {
            operator = " not like ";
        } else {
            operator = " like ";
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append(((Renderable) getMatchExpression()).render(renderingContext)).append(operator)
                .append(((Renderable) getPattern()).render(renderingContext));
//        if (getEscapeCharacter() != null) {
//            buffer.append(" escape '").append(((LiteralExpression<Character>) getEscapeCharacter()).getLiteral())
//                    .append("'");
//        }
        return buffer.toString();
    }
}
