using E_mart.Models;

namespace E_mart.Services
{
    public interface IPaymentDetailService
    {
        Task<PaymentDetail> ProcessPaymentAsync(PaymentDetail payment);
        //Task<PaymentDetail> GetPaymentDetailAsync(int OrderId);
        Task<PaymentDetail?> GetPaymentStatus(int OrderId);

    }
}
