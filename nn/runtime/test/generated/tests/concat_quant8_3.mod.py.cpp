// DO NOT EDIT;
// Generated by ml/nn/runtime/test/specs/generate_test.sh
#include "../../TestGenerated.h"

namespace concat_quant8_3 {
std::vector<MixedTypedExample> examples = {
// Generated concat_quant8_3 test
#include "generated/examples/concat_quant8_3.example.cpp"
};
// Generated model constructor
#include "generated/models/concat_quant8_3.model.cpp"
} // namespace concat_quant8_3
TEST_F(GeneratedTests, concat_quant8_3) {
    execute(concat_quant8_3::CreateModel,
            concat_quant8_3::is_ignored,
            concat_quant8_3::examples);
}
