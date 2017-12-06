package com.validators.javax;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({CustomerCheck.class, AddressCheck.class, Default.class})
public interface OrderedCustomerCheck {
}
