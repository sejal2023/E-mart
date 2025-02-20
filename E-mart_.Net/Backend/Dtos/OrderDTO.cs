namespace E_mart.Dtos
{
    public class OrderDTO
    {
        public int OrderId { get; set; }
        public double Amount { get; set; }
        public DateTime? OrderDate { get; set; }
        public string? PaymentMethod { get; set; }
        public string? Status { get; set; }
        public int CartId { get; set; }
        public int UserId { get; set; }
    }
}
