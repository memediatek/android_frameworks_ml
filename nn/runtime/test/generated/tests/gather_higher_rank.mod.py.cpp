// clang-format off
// Generated file (from: gather_higher_rank.mod.py). Do not edit
#include "../../TestGenerated.h"

namespace gather_higher_rank {
// Generated gather_higher_rank test
#include "generated/examples/gather_higher_rank.example.cpp"
// Generated model constructor
#include "generated/models/gather_higher_rank.model.cpp"
} // namespace gather_higher_rank

TEST_F(GeneratedTests, gather_higher_rank) {
    execute(gather_higher_rank::CreateModel,
            gather_higher_rank::is_ignored,
            gather_higher_rank::get_examples());
}

TEST_F(GeneratedTests, gather_higher_rank_relaxed) {
    execute(gather_higher_rank::CreateModel_relaxed,
            gather_higher_rank::is_ignored_relaxed,
            gather_higher_rank::get_examples_relaxed());
}

TEST_F(GeneratedTests, gather_higher_rank_quant8) {
    execute(gather_higher_rank::CreateModel_quant8,
            gather_higher_rank::is_ignored_quant8,
            gather_higher_rank::get_examples_quant8());
}

TEST_F(GeneratedTests, gather_higher_rank_int32) {
    execute(gather_higher_rank::CreateModel_int32,
            gather_higher_rank::is_ignored_int32,
            gather_higher_rank::get_examples_int32());
}

