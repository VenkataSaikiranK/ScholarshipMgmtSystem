package com.dal.group7.service.implementation;

import com.dal.group7.persistent.model.Scholarship;

import java.util.Arrays;

public enum AllocationStrategy {
    SLAB_A(2000000D, null) {
        @Override
        public Double getAllocatedTuitionAmount(Scholarship scholarship) {
            return 0.6 * scholarship.getTuitionAmount();
        }

        @Override
        public Boolean isApplicable(Double amount) {
            return amount > SLAB_A.lowerCap;
        }
    },
    SLAB_B(1500000D, 2000000D) {
        @Override
        public Double getAllocatedTuitionAmount(Scholarship scholarship) {

            return 0.7 * scholarship.getTuitionAmount();
        }

        @Override
        public Double getAllocatedTravelAmount(Scholarship scholarship) {
            return 0.5 * scholarship.getTravelAmount();
        }

        @Override
        public Double getAllocatedInsuranceAmount(Scholarship scholarship) {
            return 0.5 * scholarship.getInsuranceAmount();
        }
    },
    SLAB_C(1000000D, 1500000D) {
        @Override
        public Double getAllocatedTuitionAmount(Scholarship scholarship) {
            return 1 * scholarship.getTuitionAmount();
        }

        @Override
        public Double getAllocatedTravelAmount(Scholarship scholarship) {
            return 0.75 * scholarship.getTravelAmount();
        }

        @Override
        public Double getAllocatedInsuranceAmount(Scholarship scholarship) {
            return 0.75 * scholarship.getInsuranceAmount();
        }

    },
    SLAB_D(0D, 400000D) {
        @Override
        public Double getAllocatedTuitionAmount(Scholarship scholarship) {

            return scholarship.getTuitionAmount();
        }

        @Override
        public Double getAllocatedLivingExpenseAmount(Scholarship scholarship) {
            return scholarship.getLivingExpenseAmount();
        }

        @Override
        public Double getAllocatedTravelAmount(Scholarship scholarship) {
            return scholarship.getTravelAmount();
        }

        @Override
        public Double getAllocatedInsuranceAmount(Scholarship scholarship) {
            return scholarship.getInsuranceAmount();
        }
    };

    private static final double NO_AMOUNT = 0;
    private final Double lowerCap;
    private final Double upperCap;

    AllocationStrategy(Double lowerCap, Double upperCap) {
        this.lowerCap = lowerCap;
        this.upperCap = upperCap;
    }

    public static AllocationStrategy from(Double amount) {
        return Arrays.stream(AllocationStrategy.values())
                .filter(allocationStrategy -> allocationStrategy.isApplicable(amount))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public abstract Double getAllocatedTuitionAmount(Scholarship scholarship);
    public Double getAllocatedLivingExpenseAmount(Scholarship scholarship) {
        return NO_AMOUNT;
    }
    public Double getAllocatedTravelAmount(Scholarship scholarship) {
        return NO_AMOUNT;
    }
    public Double getAllocatedInsuranceAmount(Scholarship scholarship) {
        return scholarship.getInsuranceAmount();
    }

    public Boolean isApplicable(Double amount) {
        return amount > lowerCap && amount <= upperCap;
    }

}
