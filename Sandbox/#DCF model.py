#DCF model
#import numpy as np

def dcf_valuation(revenue, growth_rate, ebit_margin, tax_rate, capex_percent, wc_percent, wacc, terminal_growth, years=5, exit_multiple=None):
    """
    Calculates the Discounted Cash Flow (DCF) valuation of a company.
    
    :param revenue: Initial revenue
    :param growth_rate: Annual revenue growth rate (as decimal)
    :param ebit_margin: EBIT margin (as decimal)
    :param tax_rate: Corporate tax rate (as decimal)
    :param capex_percent: Capital expenditures as a % of revenue
    :param wc_percent: Change in working capital as % of revenue
    :param wacc: Weighted Average Cost of Capital (as decimal)
    :param terminal_growth: Perpetuity growth rate (as decimal)
    :param years: Number of years for projection
    :param exit_multiple: If provided, uses EV/EBITDA exit multiple instead of perpetuity growth.
    """
    # Forecast financials
    revenues = [revenue * (1 + growth_rate) ** i for i in range(years)]
    ebit = [rev * ebit_margin for rev in revenues]
    taxes = [eb * tax_rate for eb in ebit]
    nopat = [eb - tax for eb, tax in zip(ebit, taxes)]
    capex = [rev * capex_percent for rev in revenues]
    wc_changes = [rev * wc_percent for rev in revenues]
    fcff = [nop - cap - wc for nop, cap, wc in zip(nopat, capex, wc_changes)]
    
    # Discounted Cash Flow Calculation
    discount_factors = [(1 / (1 + wacc) ** (i + 1)) for i in range(years)]
    discounted_fcff = [fcf * df for fcf, df in zip(fcff, discount_factors)]
    npv_fcf = sum(discounted_fcff)
    
    # Terminal Value Calculation
    if exit_multiple:
        terminal_value = (ebit[-1] * exit_multiple) / (1 + wacc) ** years
    else:
        terminal_value = (fcff[-1] * (1 + terminal_growth)) / (wacc - terminal_growth)
        terminal_value /= (1 + wacc) ** years
    
    total_value = npv_fcf + terminal_value
    return total_value, discounted_fcff, terminal_value

# Example usage
if __name__ == "__main__":
    valuation, discounted_cash_flows, terminal_val = dcf_valuation(
        revenue=100, growth_rate=0.05, ebit_margin=0.2, tax_rate=0.21,
        capex_percent=0.05, wc_percent=0.02, wacc=0.1, terminal_growth=0.03, years=5
    )
    print(f"Enterprise Value: ${valuation:,.2f}")
