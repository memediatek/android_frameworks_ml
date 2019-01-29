// clang-format off
// Generated file (from: mul.mod.py). Do not edit
#include "../../TestGenerated.h"

namespace mul {
// Generated mul test
#include "generated/examples/mul.example.cpp"
// Generated model constructor
#include "generated/models/mul.model.cpp"
} // namespace mul

TEST_F(GeneratedTests, mul) {
    execute(mul::CreateModel,
            mul::is_ignored,
            mul::get_examples());
}

#if 0
TEST_F(DynamicOutputShapeTests, mul_dynamic_output_shape) {
    execute(mul::CreateModel_dynamic_output_shape,
            mul::is_ignored_dynamic_output_shape,
            mul::get_examples_dynamic_output_shape());
}

#endif
